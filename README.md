------
README
------

  1.) Q: Describe how polymorphism and dynamic binding was used in Part 1.
  
      A: First off, polymorphism stems from the biological concept of an
         organism having different forms. In this program, we use classes
         (blueprint for objects we will use) that are considered children
         of other classes. These children can do the same things as their
         parents, but we can also make them do more. 
         
         Dynamic binding is where we say an object belonging to the child
         class can be refered by the parent class.
         
         In Part I, we used Dynamic Binding by having the custom tiles
         from GameTileFactory refered to by a variable of GameTile.
         GameTileFactory contained tiles that we made by other students,
         but they were still GameTile's which enabled this to happen.
         
     
   2.) Q: Describe the ways in which your moveXXX() code was overly 
          complicated before your refactor. How did your code style 
          improve after the refactor?  Finally, what you have learned 
          about the process of refactoring code and redesigning features. 
          
          
       A: My moveXXX() was overly complicated before refactoring in multiple
          ways. To begin with, I was only able to finish the moveRight()method.
          The logic behind it invovled accessing individual rows and items
          within the rows of the game board, and this approach made things
          so convoluted that it was difficult to complete the other moveXXX()
          funtions using this approach. Also, the code was very difficult to
          read and understand. My code style improved by having one function
          called rotate() two manipulate the entire board (along with some
          other functions to help it). Also, having one direction already
          complete in moveDown(). So the task of moving the items in the
          board with all the other directions became simple. Just use the
          rotate() and the moveDown function in various ways, and eventually
          I was able to get the items to move in the correct directions. The
          code was also a lot shorter and easier to comprehend.
          
          Finally, I have learned that refactoring can make code more
          readable, improve it's flow of logic, and even make it more
          efficient (although this was not tested for).
  