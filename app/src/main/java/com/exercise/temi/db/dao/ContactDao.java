package com.exercise.temi.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.exercise.temi.db.entity.Contact;
import java.util.List;
import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    Flowable<List<Contact>> getAll();

    @Query ("SELECT * FROM contact WHERE messaged_at NOT NULL ORDER BY messaged_at DESC")
    Flowable<List<Contact>> loadAllMessaged();

    @Query("SELECT * FROM contact WHERE id IN (:userIds)")
    Flowable<List<Contact>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM contact WHERE first_name LIKE :first AND "
           + "last_name LIKE :last LIMIT 1")
    Contact findByName(String first, String last);

    @Insert(onConflict = IGNORE)
    void insertAll(Contact... contacts);

    @Insert(onConflict = IGNORE)
    void insert(Contact contact);

    @Insert (onConflict = REPLACE)
    void insertOrUpdate(Contact contact);

    @Delete
    void delete(Contact user);
}