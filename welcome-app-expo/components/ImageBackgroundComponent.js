import React from 'react';
import { View, Image, StyleSheet, Text, ImageBackground } from 'react-native';

const ImageBackgroundComponet = () => {
  return (
    <View style={styles.container}>
      <ImageBackground style={styles.image} 
      resizeMethod='contain'
      source={require('../src/img/LittleLemonBackground.png')}>
      <Text style={styles.title}>
        Little Lemon, your local Mediterranean Bistro
      </Text>
      </ImageBackground>
    </View>
  );
};

const styles = StyleSheet.create({
  image: {
    flex:1,
    justifyContent:'center',
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

export default ImageBackgroundComponet;