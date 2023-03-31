package com.example.demo.dao;

import com.example.demo.model.URL;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
//public interface URLRepository extends JpaRepository<URL,String> {
//
//    @Query("SELECT u FROM URL u WHERE u.url=?1")
//    URL findByUrl(String url);
//
//}

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;


@Repository
public interface URLRepository extends CassandraRepository<URL,String> {
    @AllowFiltering
    Optional<URL> findByUrl(String url);

}
