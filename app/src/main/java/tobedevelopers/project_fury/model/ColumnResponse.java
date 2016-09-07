package tobedevelopers.project_fury.model;

import java.util.Arrays;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class ColumnResponse extends Response{

	private Column[] columns;

	public ColumnResponse( String message ){
		this( message, new Column[]{} );
	}

	public ColumnResponse( String message, Column[] columns ){
		super( message );
		this.columns = columns;
	}

	public Column[] getColumns(){
		return columns;
	}

	@Override
	public String toString(){
		return "ColumnResponse{" +
			       "columns=" + Arrays.toString( columns ) +
			       "} " + super.toString();
	}
}
