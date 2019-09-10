# SRG-Base
Software I wrote for internship on my extra time.  Ultimately was not deployed as it was the office manager did not feel that it was in the scope of the project

It is meant to work with a database connected to HotDocs.
Briefly explained, HotDocs has a functionality where it remembers previous sessions by storing the user inputs in individual XML files.  This is practical but combursome if a user wants to load a previous session.

This code is meant to centralise the information from previous sessions by running at a scheduled time (this is not part of the code itself but is done through OS settings), scanning through where HotDocs keeps a session's inputs, if the file is new, its contents are sent to a database, and if the file has been updated, the database is also updated with the new input. 
