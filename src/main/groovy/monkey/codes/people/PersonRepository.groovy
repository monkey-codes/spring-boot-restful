package monkey.codes.people

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/**
 * Created by jzietsman on 1/25/16.
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
interface PersonRepository extends MongoRepository<Person, String>{

    List<Person> findByLastName(@Param("name") String name);
}