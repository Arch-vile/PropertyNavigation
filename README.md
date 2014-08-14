PropertyNavigation
==================

**What**
Utility to create property expression by navigating object hierarchy.

**Why**
So that you dont need to use static strings that contain risk of typos and are easily broken during refactoring.

**How**
Say you have a Book class that has Author as class variable (accessable by getter) and the Author has a name.

>prop(of(Book.class).getAuthor().getName())

Will return string "author.name".


Application examples:

Wicket: Use with PropertyModel to have refactor safe property accessors.
Instead of:
  add(new Label("myLabel", new PropertyModel(person, "name"));
You can say:
  add(new Label("myLabel", new PropertyModel(person, prop(of(Person.class).getName())));
  
Grails:
Use in the GSP pages to have compile and refactor safe bindings to command objects
Instead of:
  <g:textField name="player.name"/>
Use:
  <g:textField name="${prop(of(Command.class).getPlayer().getName()))}"/>
  
