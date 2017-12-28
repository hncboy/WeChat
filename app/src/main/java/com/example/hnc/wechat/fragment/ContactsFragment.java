package com.example.hnc.wechat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hnc.wechat.R;
import com.example.hnc.wechat.adapter.ContactsAdapter;
import com.example.hnc.wechat.bean.User;
import com.example.hnc.wechat.bean.Word;
import com.example.hnc.wechat.util.DataFactory;
import com.example.hnc.wechat.view.ContactsDividerItemDecoration;
import com.example.hnc.wechat.view.IndexView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pc on 2017/12/21.
 */

public class ContactsFragment extends Fragment {

    private RecyclerView recyclerView;
    private IndexView indexView;
    private TextView tvWord;
    private List<User> contactsList = new ArrayList<>();
    private ContactsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Word words[] = new Word[27];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        tvWord = view.findViewById(R.id.tv_word);
        indexView = view.findViewById(R.id.index_view);
        indexView.addOnIndexListener(new IndexView.OnIndexListener() {
            @Override
            public void onSelectedIndex(int index, String word) {
                tvWord.setText(word);
                if (index - 2 >= 0 && index - 2 < 27) {
                    int dex = words[index - 2].index;
                    layoutManager.scrollToPositionWithOffset(dex, 0);
                } else if (index == 0) {
                    layoutManager.scrollToPositionWithOffset(0, 0);
                } else if (index == 1) {
                    layoutManager.scrollToPositionWithOffset(2, 0);
                }
            }

            @Override
            public void onStart(int index, String word) {
                tvWord.setVisibility(View.VISIBLE);
                tvWord.setText(word);
                if (index - 2 >= 0 && index - 2 < 27) {
                    int dex = words[index - 2].index;
                    layoutManager.scrollToPositionWithOffset(dex, 0);
                } else if (index == 0) {
                    layoutManager.scrollToPositionWithOffset(0, 0);
                } else if (index == 1) {
                    layoutManager.scrollToPositionWithOffset(4, 0);
                }
            }

            @Override
            public void onEnd() {
                tvWord.setVisibility(View.GONE);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initContacts();
    }

    public void initContacts() {
        contactsList = DataFactory.createContacts(80);
        Collections.sort(contactsList, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.getName().compareTo(t1.getName());
            }
        });
        contactsList = DataFactory.createContactsFunction(contactsList);
        initWord();
        adapter = new ContactsAdapter(contactsList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ContactsDividerItemDecoration(getContext(), words));
    }

    //显示选中字母出现的黑框
    public void initWord() {
        int in = 0;
        int k = 0;
        String word = "";
        for (int i = 4; i < contactsList.size(); i++) {
            String w1 = contactsList.get(i).getName().substring(0, 1);
            //空字符则继续循环
            if (word.equals(w1)) {
                continue;
            }
            word = w1;
            in = i;
            for (; k < 26; k++) {
                String w2 = IndexView.INDEX_KEY[k + 2];
                if (word.compareTo(w2) < 0) {
                    break;
                }
                words[k] = new Word();
                words[k].title = IndexView.INDEX_KEY[k + 2];
                words[k].index = in;
            }
        }
        for (; k < 26; k++) {
            words[k] = new Word();
            words[k].title = IndexView.INDEX_KEY[k + 2];
            words[k].index = in;
        }
        words[k] = new Word();
        words[k].title = IndexView.INDEX_KEY[k + 2];
        words[k].index = in + 1;
    }
}
