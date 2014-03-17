package com.jediminer543.plugin.threads;

import com.jediminer543.plugin.GLOBALS;

public class TPSThread implements Runnable
	{
		long sec;
		int ticks;
		
		@Override
		public void run()
		{
			sec = (System.currentTimeMillis() / 1000);
			
			if(GLOBALS.second == sec)
			{
				ticks++;
			}
			else
			{
				GLOBALS.second = sec;
				GLOBALS.TPS = (GLOBALS.TPS == 0 ? ticks : ((GLOBALS.TPS + ticks) / 2));
				ticks = 0;
			}
		}
	}
