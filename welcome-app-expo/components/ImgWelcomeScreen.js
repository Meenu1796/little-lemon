import React from 'react';
import { View, Image, StyleSheet, Text } from 'react-native';

const ImgWelcomeScreen = () => {
  return (
    <View style={styles.container}>
      <Image style={styles.logo} source={require('../src/img/littleLemonLogo.png')} />
      <Text style={styles.title}>
        Little Lemon, your local Mediterranean Bistro
      </Text>
    </View>
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
});

export default ImgWelcomeScreen;