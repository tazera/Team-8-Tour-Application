Urban Science Building Tour/Open Day App

This app will be used for post applicant days and open days for students looking
at joining Newcastle University.
It will provide information about the Urban Science Building and the surrounding
area, in the form of self-guided tours and a pre-configured tour.
Directions from the local transport hubs anad the main university campus will
also be featured in the app along with some points of interest, and places to
see in Newcastle.

The app makes use of Android Studio, Google Maps, and Firebase for its
functionality. Locations for the app's use are stored in the database and will
be retrieved as and when needed. Google Maps will be used to display these
locations and create the routes between these.

Project Structure
The app is structured as follows:
The external libraries being used by the project are stored with the path;
\team-8-project\app\libs
The Java files for the app have the path;
\team-8-project\app\src\main\java\com\example\team8app
The xml files and resources for the app are stored in the folder with the path;
\team-8-project\app\src\main\res
The tests written for the app are stored in the folders with the paths;
\team-8-project\app\src\androidTest
\team-8-project\app\src\test

The link for the marketing website for the app is:
http://homepages.cs.ncl.ac.uk/2019-20/csc2022/team-08/

VIEWING THE PROJECT

To view the project, open the team-8-project directory in android studio.

Change the project view to the 'Android' view.

Click File > Sync Project with gradle files.

You should now be able to view all the project files.

If you want to run the app in android studio, the android device must have developer mode enabled, and debugging mode on.
You should not need to do this if you are running it from the APK file.


If you cannot open the project in the android studio, this is a list of where
	different parts of the project are in the team-8-project directory:
- Java Code
	- team-8-project > app > src > main > java > com > example > team8app

- XML Files (GUI) (We have some unfinished screens in Unfinished Screens.docx)
	- team-8-project > app > src > main > res > layout

- Test Code
	- team-8-project > app > src > androidTest > java > com > example > team8app

A NOTE ON TESTING

To test, we have used JUnit and Android 'Espresso' Testing.
These types of tests allow the tests to be programmed to interact with the app
	and then assert elements of the interface to ensure the interactions
	cause the correct behaviour on the app e.g. correct page loaded.
These tests require the IDE to be connected to an android device or emulator to run.
We used these tests to inform our test plan, as well as user testing by our test team.

RUNNING THE APP

To run the app, open the provided 'app-debug.apk' file on an android device and click install.
Once installed, open the app on the android device.

OUR DATABASE

We have used Google's Cloud Based database 'FireStore'.
References to this database can be found throughout the app including in Floor.java and MapsActivity.java/
No personal details were stored, only basic information about rooms in the USB and places in Newcastle.

MADE WITH GIT

We used a private GitLab repository on the NUCode.ncl.ac.uk platform as our version control technology.
