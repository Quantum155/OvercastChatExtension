# OCCChatExtension
OCC (and PGM in general) doesn't have proper chat features (like /ignore), so I made a mod to do that locally.  
I wrote this for personal use pretty much but decided to share it here just in case.

----

##### Current features:  
Ignore players (which will block private messages from appearing)  
Ability to toggle team, global, and admin chat separately  
##### TODO:  
[ ]  Prevent sound from playing when receiving a message in /msg and adminchat  
[ ] - Display current channel maybe?  

Compatible with forge 1.8.9.   
The mod won't do anything with the chat when not connected to *.oc.tc  

Main command (displays help): /chatextension  

Building locally: ./gradlew setupDecompWorkspace and ./gradlew build. You might need to update to gradle 4.0 before.

----  
Note: I don't really know java so the code might be messy but it shouldn't be that bad
