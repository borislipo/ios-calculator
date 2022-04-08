import React, {useEffect, useState} from 'react';
import {Text, View, DeviceEventEmitter} from 'react-native';
import SpinnerAndroid from '../spinnerAndroid';
import {styles} from '../theme/appTheme';

export const CalculatorScreen = () => {
  const [spinerCode, setSpinerCode] = useState("");

  useEffect(() => {

    DeviceEventEmitter.addListener('topChange', (e: any) => {
      console.log(e)
      // handle event and you will get a value in event object, you can log it here
    });


  },[])

  const onSelected = (event: any) => {
    console.log(event.nativeEvent);
    setSpinerCode(event.nativeEvent.data);
  };
const onClick=()=>{
  console.log(spinerCode);
}
  return (
    <View style={styles.container}>
      <SpinnerAndroid 
      onClick={onClick}
      onChange={onSelected} 
      style={styles.spinner} 
      dropDownWidth={150}
      />
      <View>
        <Text>{spinerCode}</Text>
      </View>
    </View>
  );
};
