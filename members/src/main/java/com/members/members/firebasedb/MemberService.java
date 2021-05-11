package com.members.members.firebasedb;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.members.members.model.Member;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class MemberService {

    private static final String COLLECTION_NAME = "members";

    public String saveMember(@NotNull Member member) throws ExecutionException, InterruptedException {

        member.createId();
        member.setImageUrl();

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)//
         .document(member.getId()).set(member);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public Member getMemberDetails(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        DocumentReference documentationReference = dbFirestore.collection(COLLECTION_NAME).document(name);

        ApiFuture<DocumentSnapshot> future = documentationReference.get();

        DocumentSnapshot document = future.get();

        Member member = null;
        if(document.exists()){
            member = document.toObject(Member.class);
            return member;
        }
        return null;
    }

    public List<Member> getMembersDetails() throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentationReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentationReference.iterator();

        List<Member> memberList = new ArrayList<>();
        Member member = null;

        while (iterator.hasNext()){
            DocumentReference documentRef = iterator.next();
            ApiFuture<DocumentSnapshot> future = documentRef.get();
            DocumentSnapshot document = future.get();

            member = document.toObject(Member.class);
            memberList.add(member);
        }
        return memberList;
    }

    public String updateMember(Member member) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)//
                .document(member.getName()).set(member);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public void deleteMember(String id) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME)//
                .document(id).delete();

        System.out.println("Member " + id + " has been deleted successfully");
    }

}
