# FileViewer
Simple pdf file viewer application based on MVI architecture.

## Selected architecture

The whole application is written in kotlin using MVI architecture pattern. This architecture was chosen due to the use cases of the project. There are only two screens, one for preview list of files and second one for displaying the chosen pdf file. There are not many UI events or input fields where it is worth using MVVM with data binding to observe each field individually. 
The MVVM architecture pattern can be too complex for applications whose UI is rather simple. Adding as much level of abstraction in such apps can result in boiler plate code that only makes the underlying logic more complicated. Also, an MVP pattern could be used here, but with patterns such as MVP or MVVM, the business logic and the Views may have a different state at any point. The MVI pattern solves the above issues by making Models represent a state rather than plain old data. Immutable data structures are very easy to handle, it can only be managed in one place and the application will have only one state between all layers.

## Used Tools

For the project I used the following tools:  
* [Room](https://developer.android.com/topic/libraries/architecture/room) - It is an abstraction layer over SQLite.
* [Work Manager](https://developer.android.com/reference/androidx/work/WorkManager) - For populating database in the background thread.
* [Gson](https://github.com/google/gson) - Used to generate entities (POJO) from json file. (Table of files from excel was mapped to json file).
* [Anko](https://github.com/Kotlin/anko/wiki/Anko-Commons-%E2%80%93-Intents) - Used to navigate between activities.
* [RxBinding](https://github.com/JakeWharton/RxBinding) - Used for background operation in observable stream.
* [PaperParcel](https://github.com/grandstaish/paperparcel) - Used for parcelization.

![file_viewer](https://user-images.githubusercontent.com/24879552/58427059-c0d6c700-809e-11e9-81d9-5df2ebbbc87c.jpg)
