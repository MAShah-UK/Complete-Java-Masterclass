package com.cjm.ms;

public class Button {
    private String title;
    private IOnClickListener onClickListener;

    public Button(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    // Allows client to supply different functionality for when a button is clicked.
    public void setOnClickListener(IOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick() {
        this.onClickListener.onClick(this.title);
    }

    public interface IOnClickListener {
        void onClick(String title);
    }
}
