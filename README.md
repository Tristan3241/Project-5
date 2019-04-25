# Project-5
Documentation

This project on eclipse looks like a mess. One of the first things I noticed when starting this project was how many lines and confusing layout blocks of code that you could probably never do again. I attempted to make it look as close to the display as possible. I used an eclipse plugin called WindowBuilder in order to help with the GUI. This helped with time tremendously. I ended up using 4 panels and group layout to individual place each component. 

I didnt use too many methods. I used a setField() method in order to initilize most of the JSwing elements however when it came to the group layouts sometimes I had to move some of the initlizations to make it work. I did my actionlisteners here as well. 

Calculate HD action listener I created a method that calculated hamming distance and stores it in a array that corresponded to each distance. As well as adding the ones that were equal o the slider value in order to display them in the JTextArea stationList. 

The showStation action listener used the method CalcHammDist in order to get the values stored in that array and set the text field to each of the values corresponding distance. 

The addStation action listener takes the string in the addStation text field turn it into all caps. It will then check if the string is already in the txt file. If it isnt in the file it turns it into all uppercase and then adds it to the ArrayList and also calls Collections.sort with the comparator based on alphabetically sorting. 

My calcHammDist method that uses an Arraylist that has the STID and then takes the string to be compared into a Char array and then takes each string in the file one by one and turns it into a char array. Then it compares each charater and adds 1 if the characters are different. Once I have that value I store it in the correspoding int array index (distance 0, Array[0]), and then i compare that value to the value of the slider and if it matched I stored it in a String array in order to update the the DropDown box that lists the station id's. 


                                                  
