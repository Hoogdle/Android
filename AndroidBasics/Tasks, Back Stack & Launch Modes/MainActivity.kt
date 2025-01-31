// source : http://youtube.com/watch?v=Z0AzoFOiH9c&list=PLQkwcJG4YTCSVDhww92llY3CAnc_vUhsm&index=2
// source : https://medium.com/mindorks/android-launch-mode-787d28952959

// Tasks, Back Stack & Launch Modes

// #BackStack
// stack of Screens of Activity.
// e.g. suppose you touch 'book mark' button. then new screen will show up to your screen
// which means, in back stack, new screen is stacked up to back stack space.
// relate to which one is top and one is bottom.

// #Task
// collection of multiple screens. e.g. zip
// kind of combines all screens in one unit

// Ex1 [#Screen1]
// you could do something on some browser app. if you touch instagram link, then instagram application will operate.
// this is the case of multiple task. one of task is browser and other is instagram.
// both task have its own backstack.
// if all the stack, which in the task of instagram, were removed, then you will go to Web-browser task.


// #LaunchMode
// launch mode allow us to set the behavior when a new activity gets pushed on the back stack.

// there is 4 type of launch modes
// (1) Standard(default)
// create a new instance of activity even there are same activity is already present.
// e.g. A → B → C → D, append B, => A → B → C → D → B

// (2) Single Top
// if same activity, which we want to generate as new, already on the 'top' of stack, then Android do not make new activity, instead receive the callback on 'onNewIntent()' method.
// e.g. A →B →C →D, append C, => A →B →C →D →C
// e.g. A →B →C →D →C, append C, => A →B →C →D →C (not generate new C Activity)

// (4) Single Task
// have only "one instance" in the system, which means if activity instance is not present then make new one, if activity instance is already present somewhere then do not generate new one, just receive call back.
// e.g. A →B →C, append D, A →B →C →D
// e.g. A →B →C →D, append B, A →B (C, D get destroyed)

// (4) Single Instance
// It is similar to singleTask except that no other activities will be created in the same task.
// means, we can choose a activity which perform alone in a single task.
// e.g. D has SingleInstance
// Task 1: A →B →C
// Task 2: D
// append E, then,
// Task 1: A →B →C →E
// Task 2: D

// if we launch D in some context the we will get callback of D activity.

