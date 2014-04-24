package common;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import objects.Account;
import objects.GameServer;

public class Realm
{
	private static ConcurrentMap<Integer, Account> Accounts = new ConcurrentHashMap<Integer,Account>(16, 0.75f, 3);//by GUID
	private static ConcurrentMap<String, Integer> Accounts2 = new ConcurrentHashMap<String, Integer>(16, 0.75f, 3);//by Name
	public static ConcurrentMap<Integer, GameServer> GameServers = new ConcurrentHashMap<Integer, GameServer>(16, 0.75f, 3);
	public static String BAN_IP = "";
	
	public static boolean IPcompareToBanIP(String ip)
	{
		String[] split = BAN_IP.split(",");
		for(String ipsplit : split)
		{
			if(ip.compareTo(ipsplit) == 0) return true;
		}
		
		return false;
	}
	
	public static void loadRealm()
	{
		System.out.println("=== Chargement du serveur de connexion ===");
		
		System.out.print("Chargement des serveurs de jeu : ");
		SQLManager.LOAD_SERVERS();
		System.out.println(GameServers.size()+" serveur(s) de jeu charge(s)");
		
		System.out.print("Chargement des BAN_IP : ");
		int nbr = SQLManager.LOAD_BANIP();
		System.out.println(nbr+" IP(s) charge(s)");
		
		System.out.print("Remise a 0 des CurIP(s) : ");
		SQLManager.RESET_CUR_IP();
		System.out.println("OK!");
		
		Ancestra.isRunning = true;
	}
	
	public static void addAccount(Account acc)
	{
		if (Accounts.containsKey(acc.get_GUID()))
		{
			Accounts2.remove(acc.get_name());
			Accounts.remove(acc.get_GUID());
		}
		Accounts.put(acc.get_GUID(), acc);
		Accounts2.put(acc.get_name().toLowerCase(), acc.get_GUID());
	}
	
	public static ConcurrentMap<Integer, Account> getAccountsMap()
	{
		return Accounts;
	}
	
	public static void deleteAccount(Account acc)
	{
		Accounts.remove(acc.get_GUID());
		Accounts2.remove(acc.get_name().toLowerCase());
	}
	
	public static Account getCompteByID(int guid)
	{
		return Accounts.get(guid);
	}
	
	public static Account getCompteByName(String name)
	{
		int guid = -1;
		try
		{
			guid = Accounts2.get(name.toLowerCase());
		}catch(Exception e)
		{
			return null;
		}
		return Accounts.get(guid);
	}
	
}
