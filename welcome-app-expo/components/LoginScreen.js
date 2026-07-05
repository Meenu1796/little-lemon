import { ScrollView, Text, StyleSheet, TextInput, Alert, Pressable} from 'react-native';
import React, { useState } from 'react';

export default function LoginScreen() {

  const [email, setEmail]=useState('');
  const [password, setPassword] = useState('');
  const [showLogin, setShowLogin] = useState(false);

  return (
    <ScrollView style={styles.container}>
      <Text style={styles.headerText}>Welcome to Little Lemon</Text>
      { showLogin && <Text style={styles.regularText}>Logged in successfully!</Text>}
      { !showLogin && (
        <>
        <Text style={styles.regularText}>Login to continue </Text>
      <TextInput 
      style={styles.input}
      placeholder={'email'}
      value={email}
      onChangeText={setEmail}
      keyboardType={"email-address"}
      onFocus={() => Alert.alert('First name is focused')}
      onBlur={()=> Alert.alert('first name is now blurred')}
      clearButtonMode='always'
      />
      <TextInput 
      style={styles.input}
      placeholder={'password'}
      value={password}
      onChangeText={setPassword}
      secureTextEntry={'true'}
      keyboardType={'default'}
      />
      <Pressable style={styles.button} onPress={ ()=> {
        setShowLogin(!showLogin)
      }}>
      <Text style={styles.buttonText}>{showLogin ? 'Logout' : 'Login'}</Text>
      </Pressable>
        </>
      )}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  headerText: {
    padding: 40,
    fontSize: 30,
    color: '#EDEFEE',
    textAlign: 'center',
  },
  regularText: {
    fontSize: 24,
    padding: 20,
    marginVertical: 8,
    color: '#EDEFEE',
    textAlign: 'center',
  },
  input: { 
    height: 40, 
    margin: 12, 
    borderWidth: 1, 
    padding: 10, 
    fontSize: 16, 
    borderColor: 'EDEFEE', 
    backgroundColor: '#FFFFFF', 
  }, 
button: {
    fontSize: 22,
    padding: 10,
    marginVertical: 8,
    margin: 40,
    textAlign:'center',
    backgroundColor: '#EE9972',
    borderWidth: 2,
    borderRadius: 20
  },
  buttonText: {
    color: '#FFFFFF',
    textAlign: 'center',
    fontSize: 28,
  },
});

