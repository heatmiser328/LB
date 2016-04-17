package ica.LB.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ica.LB.R;

import java.util.*;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class BattleListAdapter extends BaseExpandableListAdapter {

    private Activity context = null;
    private List<ica.LB.Core.Battle> battles = new ArrayList<ica.LB.Core.Battle>();

	public BattleListAdapter (Activity c, List<ica.LB.Core.Battle> l) {
		context = c;
		battles = l;
	}

    @Override
	public Object getChild (int groupPosition, int childPosition) {
		return battles.get(groupPosition).getScenarios().get(childPosition);
	}
    
    @Override
	public long getChildId (int groupPosition, int childPosition) {
		return battles.get(groupPosition).getScenarios().get(childPosition).getId();
	}
    
    @Override
	public int getChildrenCount (int groupPosition) {
		return battles.get(groupPosition).getScenarios().size();
	}
	
    @Override
    public Object getGroup (int groupPosition) {
		return battles.get(groupPosition);
	}
	
    @Override
    public long getGroupId (int groupPosition) {
		return battles.get(groupPosition).getId();
	}
	
    @Override
    public boolean isChildSelectable (int groupPosition, int childPosition) {
		return true;
	}
	
    @Override
    public int getGroupCount() {
        return battles.size();
	}
	
    @Override
    public boolean hasStableIds() {
        return true;
	}

    @Override
	public View getGroupView (int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		// Get our object for position
        ica.LB.Core.Battle item = battles.get(groupPosition);

        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;
        if (convertView == null) 
            view = inf.inflate(R.layout.battlelistitem, parent, false);

		// Find references to each subview in the list item's view
        ImageView imageView = (ImageView) view.findViewById(R.id.logo);
		TextView txtName = (TextView)view.findViewById(R.id.textName);
		TextView txtPublisher = (TextView)view.findViewById(R.id.textPublisher);

		//Assign item's values to the various subviews
        int resid = context.getResources().getIdentifier("drawable/" + item.getImage(), null, context.getPackageName());
        //ExpandableListView ev = (ExpandableListView)parent;
        //ev.setGroupIndicator(context.getResources().getDrawable(resid));
        imageView.setImageResource(resid);
		txtName.setText (item.getName(), TextView.BufferType.NORMAL);
		txtPublisher.setText (item.getPublisher(), TextView.BufferType.NORMAL);

		//Finally return the view
		return view;
	}

    @Override
	public View getChildView (int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ica.LB.Core.Scenario item = battles.get(groupPosition).getScenarios().get(childPosition);

        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;
        if (convertView == null)
            view = inf.inflate(R.layout.scenariolistitem, parent, false);

		TextView txtName = (TextView)view.findViewById(R.id.textName);
		txtName.setText (item.getName(), TextView.BufferType.NORMAL);

		TextView txtDate = (TextView)view.findViewById(R.id.textDate);
		txtDate.setText (item.toString(), TextView.BufferType.NORMAL);

		return view;
	}

}
