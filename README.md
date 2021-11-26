
# Saif Karnawi - My Personal Project - CPSC 210

## University Student Schedule - USS

This program is designed for ***post-secondary school students***. The application will have three main functions: 


- Display some sort of visual representation of class times once the courses and their times are added.
- Help the user learn more about the minimum and maximum hours they will spend on their courses each day or month for a certain term.
- Generate a difficulty out of 5 for the workload. Students will then be able to compare this number to that of their classmates (once their classmates also use the program and learn their term difficulty).

Additionally, students might also be interested in comparing the difficulties of their first and second term courses - they can do that as well.

*This project interests me because I have always found selecting courses an enjoyable process, and being more careful with the selections will make a huge difference in terms of school / life balance*. Simply switching two courses between terms or deciding to take a course during the summer term can impact grades, stress levels, and lifestyle during a term.  


## User Stories
- As a user, I want to be able to add a class X to the list of classes I am taking for the term Y.
- As a user, I want to be able to view my classes and their times.
- As a user, I want to be able to input what I think the difficulty is for each course out of 5. 
- As a user, I want to be able to see my term difficulty average out of 5.
- As a user, I want to be able to input how many minimum and maximum weekly hours each course requires. 
- As a user, I want to be able to view the minimum and maximum amount of hours I will be studying every day or every month.

## User Stories for Phase 2
- As a user, I want to be able to save my info / entry after the information is displayed to me.
- As a user, I want to be able to load up the most previous entry. 

# Phase 4 TASK 2: 
Wed Nov 24 13:41:03 PST 2021
A new term has been created

#Phase 4 TASK 3:
Looking at my design, there is some coupling, which I can improve upon. For example, both the AppGUI
and UniversityStudentSchedule make the same amount of course, Json Writer, and Json Reader Objects. If was
to change this, I would possibly make them extend a class that has a list of courses, and a json reader /
writer.

In addition, courses are being initiated from two different classes and are associated with three
classes. Both the GUI and console class (UniversityStudentScheduleApp) initiate course objects
and then add the course to a TermCourses class, where there is a linked list of type course. 
The way I would change this is make a createCourse method in TermCourses and just pass it the
info related to the course. That way, Course is only associated with TermCourses, where 
termCourses can have from zero to infinity courses. In other words, AppGui and
UniversityStudentScheduleApp don't directly create Course Objects.