package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin.
    String QUESTION_2 = "SELECT * FROM ogrenci AS o" +
            "INNER JOIN islem AS i" +
            "ON o.ogrno=i.ogrno ";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT *" +
            "FROM ogrenci AS o" +
            "LEFT JOIN islem AS i" +
            "ON o.ogrno = i.ogrno" +
            "WHERE i.kitapno IS NULL;";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    String QUESTION_8 = "";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    String QUESTION_9 = "";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
