package com.example.kunalparte.themoviedbtask.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class ScrollToPaginateListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager linearLayoutManager;
    protected abstract void loadMore();
    public abstract boolean isLastPage();
    public abstract boolean isLoading();
    public abstract int getTotalPageCount();

    public ScrollToPaginateListener(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy ){
        super.onScrolled(recyclerView,dx,dy);
        int visibleItemCount = linearLayoutManager.getChildCount();
        int totalItemCount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if (!isLoading() && !isLastPage()){
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0 ){
                loadMore();
            }
        }
    }

}
