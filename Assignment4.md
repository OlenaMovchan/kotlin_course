# Assignment instructions

Use the same Pattern that we followed for the POST endpoint for the bean validation in the CourseControllerIntgTest.

Check this link for CourseControllerIntgTest example.

Create the InstructorControllerUnitTest.kt

Add the necessary spring related annotations to make sure the unit test work as expected

Create a test function addInstructor_Validation

Use the WebClient to invoke the POST endpoint /v1/instructors.

Assert the validation message instructorDTO.name must not be blank as part of the test case.

---

## Questions for this assignment

Write a test case in the InstructorControllerUnitTest and pass the name of the instructor as empty String and validate the error message that's thrown from the webClient call.
