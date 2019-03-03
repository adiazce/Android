package pe.ladrilloslark.jobs;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.LayoutJobs>  {

    List<Job> jobs;

    public JobAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public LayoutJobs onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_layout,viewGroup,false);
        LayoutJobs layoutJobs = new LayoutJobs(view);

        return layoutJobs;
    }

    @Override
    public void onBindViewHolder(@NonNull LayoutJobs layoutJobs, int i) {
        Job job = jobs.get(i);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        layoutJobs.tvTitle.setText(Html.fromHtml(job.getTitle(), Html.FROM_HTML_MODE_COMPACT));
        }else{
            layoutJobs.tvTitle.setText(Html.fromHtml(job.getTitle() ));
        }
        layoutJobs.tvCompany.setText(job.getCompany());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            layoutJobs.tvDescription.setText(Html.fromHtml(job.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        }else{
            layoutJobs.tvDescription.setText(Html.fromHtml(job.getDescription()));
        }

        Glide.with(layoutJobs.itemView.getContext()).load(job.getCompany_logo()).into(layoutJobs.ivLogo);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class LayoutJobs extends RecyclerView.ViewHolder{
        TextView tvTitle, tvCompany , tvDescription;
        ImageView ivLogo;

        public LayoutJobs(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCompany = itemView.findViewById(R.id.tvCompany);
            tvDescription =itemView.findViewById(R.id.tvDescription);
            ivLogo = itemView.findViewById(R.id.ivLogo);
        }
    }
}
