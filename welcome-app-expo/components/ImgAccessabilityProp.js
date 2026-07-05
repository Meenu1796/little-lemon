import React from 'react';
import { View, Image, StyleSheet, Text, ScrollView } from 'react-native';

const ImgAccessabilityProp = () => {
  return (
    <ScrollView style={styles.container}>
      <Image 
        style={styles.logo} 
        source={require('../src/img/littleLemonLogo.png')}
        accessible={true}
        accessibilityLabel={"Little Lemon Logo"} />
      <Text style={styles.title}>
        Little Lemon, your local Mediterranean Bistro
      </Text>
      <Image 
        style={styles.image} 
        source={require('../src/img/towel.png')}
        accessible={true}
        accessibilityLabel={"Towel"} />
        <Image 
        style={styles.image} 
        source={require('../src/img/spoon.png')}
        accessible={true}
        accessibilityLabel={"Spoon"} />
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  logo: {
     height: 100,
     width: 300,
     resizeMode: 'contain',
  },
  image: {
     height: 300,
     width: 250,
     borderWidth:2,
     borderRadius:10,
     borderColor:'red',
     resizeMode: 'repeat',
  },
  container: {
    flex: 1,
    padding: 24,
    marginTop: 25,
    backgroundColor: '#fff',
  },

  title: {
    marginTop: 16,
    paddingVertical: 10,
    color: '#333333',
    textAlign: 'center',
    fontSize: 20,
    fontWeight: 'bold',
  },
});

export default ImgAccessabilityProp;