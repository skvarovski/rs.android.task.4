# Task 4: Working with storage
![alt text](https://github.com/rolling-scopes-school/rs.android.task.4/blob/master/TaskDescritpion.svg)

The task is to create Database of cats/dogs/auto(anything you want)
1. You should show all items form your database in the recycler view on the main screen
2. After clicking "Add" button user should be redirected to "Add item screen" where he could add new item to the database
3. On the main screen user can click "Filter" button and go the the filter screen which is implemented using "PrefereneFragment"!
4. You should also add Update and Delete functionality!
5. The app should provide two implementations(first for Cursors and second for Room), and should be able to change implementation in runtime(by button click, settings, etc.)

# When change DB have a crash
#  Expected:
#  TableInfo{name='cars', columns={name=Column{name='name', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, id=Column{name='id', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=1, defaultValue='null'}, color=Column{name='color', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, year=Column{name='year', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}}, foreignKeys=[], indices=[]}
#  Found:
#  TableInfo{name='cars', columns={color=Column{name='color', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, year=Column{name='year', type='INTEGER', affinity='3', notNull=true, primaryKeyPosition=0, defaultValue='null'}, name=Column{name='name', type='TEXT', affinity='2', notNull=true, primaryKeyPosition=0, defaultValue='null'}, id=Column{name='id', type='INTEGER', affinity='3', notNull=false, primaryKeyPosition=1, defaultValue='null'}}, foreignKeys=[], indices=[]}

