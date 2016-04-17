package ica.LB.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import ica.LB.FireCombatFragment;
import ica.LB.MeleeCombatFragment;
import ica.LB.MoraleFragment;
import ica.LB.GeneralFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 4;
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
            return new FireCombatFragment();
		case 1:
            return new MoraleFragment();
		case 2:
			return new MeleeCombatFragment();
		case 3:
            return new GeneralFragment();
		}
		return new Fragment();
	}

	@Override
	public String getPageTitle(int position) {
		switch (position) {
			case 0:
				return "Fire";
			case 1:
				return "Morale";
			case 2:
				return "Melee";
			case 3:
				return "General";
		}
		return "";
	}
}
