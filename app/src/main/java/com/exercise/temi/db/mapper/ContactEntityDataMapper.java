package com.exercise.temi.db.mapper;

import com.exercise.temi.db.entity.Contact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by adi.matzliah on 07/03/2018.
 */
public class ContactEntityDataMapper {

    public static com.exercise.temi.network.model.Contact convertToDatabaseModel(){
        com.exercise.temi.network.model.Contact newContact = new com.exercise.temi.network.model.Contact();
        return newContact;
    }

    public static Contact transform(com.exercise.temi.network.model.Contact contact){
        Contact newContact = new Contact();

        newContact.setId(contact.getId());
        newContact.setFirstName(contact.getFirstName());
        newContact.setLastName(contact.getLastName());
        newContact.setAddress(contact.getAddress());
        newContact.setAvatar(contact.getAvatar());
        newContact.setDate(contact.getDate());
        newContact.setEmail(contact.getEmail());
        newContact.setGender(contact.getGender());
        newContact.setJob(contact.getJob());
        newContact.setPhone(contact.getPhone());
        newContact.setLastMsgDate(contact.getLastMsgDate());

        return newContact;
    }

    public static com.exercise.temi.network.model.Contact transform(Contact contact){
        com.exercise.temi.network.model.Contact newContact = new com.exercise.temi.network.model.Contact();

        newContact.setId(contact.getId());
        newContact.setFirstName(contact.getFirstName());
        newContact.setLastName(contact.getLastName());
        newContact.setAddress(contact.getAddress());
        newContact.setAvatar(contact.getAvatar());
        newContact.setDate(contact.getDate());
        newContact.setEmail(contact.getEmail());
        newContact.setGender(contact.getGender());
        newContact.setJob(contact.getJob());
        newContact.setPhone(contact.getPhone());
        newContact.setLastMsgDate(contact.getLastMsgDate());

        return newContact;
    }

    /**
     * Transform a List of {@link com.exercise.temi.network.model.Contact} into a Collection of {@link Contact}.
     *
     * @param contactEntityCollection Object Collection to be transformed.
     * @return {@link Contact} if valid {@link com.exercise.temi.network.model.Contact} otherwise null.
     */
    public List<Contact> transform(Collection<com.exercise.temi.network.model.Contact> contactEntityCollection) {
        final List<Contact> contactList = new ArrayList<>();
        for (com.exercise.temi.network.model.Contact contactEntity : contactEntityCollection) {
            final Contact contact = transform(contactEntity);
            if (contact != null) {
                contactList.add(contact);
            }
        }
        return contactList;
    }


}
