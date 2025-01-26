package org.jvnet.basicjaxb_annox.xml.bind;

import java.lang.reflect.Member;

import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.runtime.Location;

public class LocatableUtils
{
	public static Locatable getLocatable(final Package thePackage)
	{
		if ( thePackage == null )
			return null;
		else
		{
			return new Locatable()
			{
				@Override
				public Locatable getUpstream()
				{
					return null;
				}

				@Override
				public Location getLocation()
				{
					return new Location()
					{
						@Override
						public String toString()
						{
							return thePackage.getName();
						}
					};
				}
			};
		}
	}

	public static Locatable getLocatable(final Class<?> theClass)
	{
		if ( theClass == null )
			return null;
		else
		{
			return new Locatable()
			{
				@Override
				public Locatable getUpstream()
				{
					return getLocatable(theClass.getPackage());
				}

				@Override
				public Location getLocation()
				{
					return new Location()
					{
						@Override
						public String toString()
						{
							return theClass.getName();
						}
					};
				}
			};
		}
	}

	public static Locatable getLocatable(final Member theMember)
	{
		if ( theMember == null )
			return null;
		else
		{
			return new Locatable()
			{
				@Override
				public Locatable getUpstream()
				{
					return getLocatable(theMember.getDeclaringClass());
				}

				@Override
				public Location getLocation()
				{
					return new Location()
					{
						@Override
						public String toString()
						{
							return theMember.getName();
						}
					};
				}
			};
		}
	}
}
