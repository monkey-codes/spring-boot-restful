package codes.monkey.people

import org.springframework.data.annotation.Id

class Person {
    @Id
    String id

    String firstName, lastName
}
