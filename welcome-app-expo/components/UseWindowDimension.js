import {
  ScrollView,
  Image,
  StyleSheet,
  Text,
  useWindowDimensions,
} from 'react-native';

const UseWindowDimension = () => {
  const window = useWindowDimensions();
  return (
    <ScrollView style={styles.container}>
      <Image
        style={styles.logo}
        source={require('../src/img/littleLemonLogo.png')}
        resizeMode="center"
        accessible={true}
        accessibilityLabel={'Little Lemon Logo'}
      />
      <Text style={styles.title}>
        Little Lemon, your local Mediterranean Bistro
      </Text>
      <Text style={styles.regularText}>Window Dimensions</Text>
      <Text style={styles.regularText}>Height: {window.height}</Text>
      <Text style={styles.regularText}>Width: {window.width}</Text>
      <Text style={styles.regularText}>Font scale: {window.fontScale}</Text>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  logo: {
    height: 100,
    width: 300,
    resizeMode: 'contain',
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
  regularText: {
    fontSize: 24,
    marginVertical: 8,
    color: '#000000',
    textAlign: 'left',
  }
});

export default UseWindowDimension;
