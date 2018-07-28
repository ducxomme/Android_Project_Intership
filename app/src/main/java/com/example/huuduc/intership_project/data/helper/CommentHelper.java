package com.example.huuduc.intership_project.data.helper;

import com.example.huuduc.intership_project.data.listener.CommentListener;
import com.example.huuduc.intership_project.data.listener.UserListener;
import com.example.huuduc.intership_project.data.model.Comment;
import com.example.huuduc.intership_project.data.model.CommentDate;
import com.example.huuduc.intership_project.data.model.User;
import com.example.huuduc.intership_project.utils.Constant;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommentHelper {
    public static DatabaseService mDatabase = DatabaseService.getInstance();
    public static DatabaseReference mCommentRef = mDatabase.createDatabase(Constant.COMMENT_PREFERENCES);

    public static void getAllComment(String roomID, CommentListener commentListener) {
        mCommentRef.child(roomID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Comment> listComment = new ArrayList<>();
                List<CommentDate> cmtOfDate = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Comment comment = data.getValue(Comment.class);
                    listComment.add(comment);
                }

                for (Comment comment : listComment) {
                    CommentDate commentDate = new CommentDate();
                    commentDate.setUsername(comment.getUsername());
                    commentDate.setCommentId(comment.getComment_id());
                    commentDate.setContent(comment.getContent());

                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy", new Locale("vi", "VN"));
                    Date dateParse;
                    try {
                        dateParse = df.parse(comment.getDate());
                        commentDate.setDate(dateParse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    cmtOfDate.add(commentDate);
                }

                Collections.sort(cmtOfDate, new Comparator<CommentDate>() {
                    @Override
                    public int compare(CommentDate commentDate1, CommentDate commentDate2) {
                        return commentDate1.getDate().compareTo(commentDate2.getDate());
                    }
                });
                Collections.reverse(cmtOfDate);
                listComment.clear();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                for (CommentDate commentDate : cmtOfDate) {
                    String cmtDate = df.format(commentDate.getDate());

                    Comment comment = new Comment(commentDate.getCommentId(),
                            commentDate.getContent(),
                            cmtDate,
                            commentDate.getUsername());
                    listComment.add(comment);
                }
                commentListener.success(listComment);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                commentListener.failed(databaseError.getMessage());
            }
        });
    }

    public static void putComment(String comment, String roomID) {
        String key = mCommentRef.child(roomID).push().getKey();
        Comment cmt = new Comment();
        cmt.setComment_id(key);
        cmt.setContent(comment);
        UserHelper.getAllUserInfo(new UserListener() {
            @Override
            public void success(User user) {
                cmt.setUsername(user.getUsername());

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                Date today = Calendar.getInstance().getTime();
                cmt.setDate(df.format(today));

                mCommentRef.child(roomID).child(key).setValue(cmt);
            }

            @Override
            public void failed(String error) {}
        });



    }
}
