package monkey.codes.people

import org.springframework.data.annotation.Id

/**
 * Created by jzietsman on 1/25/16.
 */
class Person {
    @Id
    String id

    String firstName, lastName
}
