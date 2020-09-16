
AUTHOR: Lluis Hernández Meliá
        email:lluis.hernandez01@estudiant.upf.edu
	

------------------------README------------------------------
Description:

The objective of the application is to solve a big problem on skaters world, the communication.
The application tries to solve this issue implementing an event system that consists on calls to a server that connects to firebase. 
On changing the selected date of the calendar, then it shows the events related to that date. If the user connected wants to participate on that event,
he can click a button that connects to the server and adds 1 to the counter of assistants to the event. The server now is not working, but it's a good example of a web App fully programmed by me (frontend+backend with firebase).
--------------------------------------------------
Bugs & problems:

The application has some problems that should be resolved, like for example using for-if instead of the queries of firebase. This is because i tried to use the realtime feature of firebase and in the end it wasn't the best option to do queries, but I didn't have time to solve it.
--------------------------------------------------
To Do:

In the future it would be great to implement:
-A map for setting the location.
-a list of top 5 most popular events.
-Finishing the filter of types of events.
-Implementing the user-password method with firebase logging in with google (firebase supports it).
-Not letting a user click the button of assistance more than once.
-------------------------------------------------
Files:

In the zip there are various files:
main.css--> the main css.
main.html-->main html webpage.
login.html, selector.html-->login webpage. It's the webpage to open if you want to see the implementation on a browser.
main.js, login.js,servidor.js-->main javascripts code.
--------------------------------------------------
