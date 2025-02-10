package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin.
    String QUESTION_2 = "SELECT o.ogrno , o.ad, o.soyad, o.sinif, o.cinsiyet, o.dtarih, o.puan " +
            "FROM ogrenci AS o " +
            "INNER JOIN islem AS i ON o.ogrno = i.ogrno;";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT ogrenci.ogrno , ogrenci.ad, ogrenci.soyad, ogrenci.sinif,ogrenci.cinsiyet,ogrenci.dtarih, ogrenci.puan " +
            "FROM ogrenci  " +
            "LEFT JOIN islem  " +
            "ON ogrenci.ogrno = islem.ogrno " +
            "WHERE islem.kitapno IS NULL ";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT o.sinif, COUNT(i.kitapno)  " +
            "FROM ogrenci AS o " +
            "LEFT JOIN islem AS i ON o.ogrno = i.ogrno " +
            "WHERE o.sinif IN ('10A', '10B') " +
            "GROUP BY o.sinif;";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT(*) FROM ogrenci";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT COUNT (DISTINCT ad) FROM ogrenci";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT ad, COUNT(*) " +
            "FROM ogrenci " +
            "GROUP BY ad;";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    String QUESTION_8 = "SELECT sinif, COUNT(*) " +
            "FROM ogrenci " +
            "GROUP BY sinif";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    String QUESTION_9 = "SELECT ad, soyad, COUNT(*)\n" +
            "FROM ogrenci AS o\n" +
            "LEFT JOIN islem AS i\n" +
            "ON i.ogrno = o.ogrno\n" +
            "GROUP BY ad, soyad;";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
