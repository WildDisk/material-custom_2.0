package com.example.picassotutorial.adapters.java;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.example.picassotutorial.R;
import com.example.picassotutorial.ui.java.PictureActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private static final String IMAGE_URL_KEY = "IMAGE_URL";
    private final Context mActivityContext;
    private final LayoutInflater mInflater;
    private final Picasso mPicasso;

    private final String[] mUrlStrings = {
            "https://upload.wikimedia.org/wikipedia/commons/c/c4/NGC253_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/6/68/NGC2276_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/e5/NGC2403_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/9/94/NGC2683_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/3/3a/NGC3169_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/a8/NGC3344_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/b/b2/NGC4038_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/5/54/NGC4395_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/5/5b/NGC4414_Spiral_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/c/c0/NGC4490_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/7/78/NGC4565_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/8/84/NGC4676_Mice_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/5/53/NGC4910_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/1/19/NGC536_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/9/92/NGC5364_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/a0/NGC5426_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/a/a5/NGC5859_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/1/12/NGC5529_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/2/29/NGC5850_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"
    };

    public ImagesAdapter(@Nullable Context context) {
        mActivityContext = context;
        mInflater = LayoutInflater.from(context);
        mPicasso = Picasso.with(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.view_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mPicasso.cancelRequest(holder.imageView);
        holder.imageView.setImageBitmap(null);
        mPicasso
                .load(mUrlStrings[position])
                .placeholder(R.drawable.ic_image)
                .resizeDimen(
                        R.dimen.image_size,
                        R.dimen.image_size
                )
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.imageView.setVisibility(View.VISIBLE);
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.imageView.setVisibility(View.INVISIBLE);
                        holder.progressBar.setVisibility(View.VISIBLE);
                    }
                });
        holder.itemView.setOnClickListener(v ->
                mActivityContext.startActivity(
                        new Intent(mActivityContext, PictureActivity.class)
                                .putExtra(IMAGE_URL_KEY, mUrlStrings[position])
                ));
    }

    @Override
    public int getItemCount() {
        return mUrlStrings.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final ProgressBar progressBar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.view_image);
            progressBar = itemView.findViewById(R.id.view_progress);
        }
    }
}
