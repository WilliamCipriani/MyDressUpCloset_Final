package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.example.entities.Report;
import com.example.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	public Users findByUsername(String username);

	public Users findByDni(String username);

	public Users findByEmail(String username);

	@Query(value = "Select u FROM User u WHERE u.email =:email and u.password =:pass", nativeQuery = true)
	public Users findByEmailAndPassword(@Param("email") String email, @Param("pass") String pass);

	@Query(value = "Select p FROM Users p WHERE p.type =:type")
	public List<Users> findByRole(@Param("type") int type);

	@Query(value = "select new com.example.entities.Report(u.id as id, u.name as name, count(p.id) as quantity) from Purchase p INNER JOIN Users u ON u.id = p.user.id group by u.id order by count(p.id) desc")
	public List<Report> userReport();
}
