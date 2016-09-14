package tobedevelopers.project_fury.dashboard;

import android.util.Pair;

import java.util.ArrayList;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/09/2016.
 */
public class TaskHolder{

	private ArrayList< Pair< Task, Column > > pairList;

	public TaskHolder(){
		pairList = new ArrayList<>();
	}

	public void addPair( Task task, Column column ){
		pairList.add( new Pair<>( task, column ) );
	}

	public ArrayList< Pair< Task, Column > > getPairList(){
		return pairList;
	}
}
