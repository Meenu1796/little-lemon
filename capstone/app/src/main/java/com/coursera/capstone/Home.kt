package com.coursera.capstone

import android.R.attr.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlin.collections.emptyList

// Button(
//            onClick = { navController.navigate(Profile.route) },
//            modifier = Modifier.padding(top = 16.dp)
//        )

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    LittleLemonTheme {
//        // preview a static version without database
//    }
//    //Home(navController = rememberNavController())
//}

@Composable
fun Home(navController: NavHostController, database: AppDatabase) {
    val menuItems by database.menuItemDao().getAllMenuItems().observeAsState(emptyList())

    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    // Get unique categories
    val categories = menuItems.map { it.category }.distinct()

    // Filter by category first, then by search phrase
    val filteredItems = menuItems
        .filter { selectedCategory.isBlank() || it.category == selectedCategory }
        .filter { searchPhrase.isBlank() || it.title.contains(searchPhrase, ignoreCase = true) }


    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Header(navController = navController)

        LazyColumn {
            item {
                // Hero section
                HeroSection(
                    searchPhrase = searchPhrase,
                    onSearchChange = { searchPhrase = it }
                )
            }
            item {
                Text(
                    text = "ORDER FOR DELIVERY!",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                MenuBreakdown(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { category ->
                        selectedCategory = if (selectedCategory == category) "" else category
                    }
                )
            }
            items(filteredItems) { item ->
                MenuItem(item)
            }

        }
    }
}

@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .padding(top = 56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        // Logo - replace R.drawable.logo with your actual logo resource
        Image(
            painter = painterResource(id=R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Little Lemon",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.DarkGreen)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "User Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable {
                    navController.navigate(Profile.route)
                },
            contentScale = ContentScale.Crop
        )

    }
}

@Composable
fun HeroSection(
    searchPhrase: String,
    onSearchChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.DarkGreen))
            .padding(16.dp)
    ) {
        Text(
            text = "Little Lemon",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.LemonYellow)
        )
        Text(
            text = "Chicago",
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            // Hero image - replace R.drawable.hero_image with your actual image resource
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = searchPhrase,
            onValueChange = onSearchChange,
            placeholder = { Text("Enter Search Phrase") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            singleLine = true
        )
    }
}

@Composable
fun MenuBreakdown(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val darkGreen = colorResource(R.color.DarkGreen)
    val lemonYellow = colorResource(R.color.LemonYellow)

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                val isSelected = selectedCategory == category
                Button(
                    onClick = { onCategorySelected(category) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) darkGreen else lemonYellow,
                        contentColor = if (isSelected) Color.White else darkGreen
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = category.replaceFirstChar { it.uppercase() },
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemRoom) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(R.color.CharcoalGray)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.description,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${item.price}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.DarkGreen)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            GlideImage(
                model = item.image,
                contentDescription = item.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun MenuItems(items: List<MenuItemRoom>) {
    Column {
        items.forEach { item ->
            MenuItem(item)
        }
    }
}
