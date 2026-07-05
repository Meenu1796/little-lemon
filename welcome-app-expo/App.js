import * as React from 'react';
import { View, StyleSheet } from 'react-native';

import LittleLemonHeader from './components/LittleLemonHeader';
import LittleLemonFooter from './components/LittleLemonFooter';
import MenuItems from './components/MenuItems';
import FeedbackForm from './components/FeedbackForm';
import KeyboardComponent from './components/KeyboardComponent';
import TextInputComponent from './components/TextInputComponent';
import TextInputProps from './components/TextInputProps'
import LoginScreen from './components/LoginScreen';
import MenuItemsPressable from './components/MenuItemsPressable';
import WelcomeScreen from './components/WelcomeScreen';
import ImgWelcomeScreen from './components/ImgWelcomeScreen';
import ImgAccessabilityProp from './components/ImgAccessabilityProp'
 import ImageExercise from './components/ImageExercise';
 import ImageBackgroundComponent from './components/ImageBackgroundComponent';
 import UseColorScheme from './components/UseColorScheme';
 import UseWindowDimension from './components/UseWindowDimension';

export default function App() {
  return (
    <>
      <View style={styles.container}>
        <LittleLemonHeader />
        <LoginScreen />
      </View>
      <View style={styles.footerContainer}>
        <LittleLemonFooter />
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#333333',
  },
  footerContainer: { backgroundColor: '#333333' },
});