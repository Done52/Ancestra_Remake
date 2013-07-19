package objects;

import java.util.ArrayList;
import java.util.Map.Entry;

import objects.Fight.Fighter;

import common.Constants;

public class QuestObjective {
	
	private final int id;
	private final int type;
	private final String args;
	private boolean isFinished;
	
	public QuestObjective (final int id, final int type, final String args)
	{
		this.id = id;
		this.type = type;
		this.args = args;
	}
	
	@SuppressWarnings("unchecked")
    public void fill(final Personnage player, final Object object)
	{
		final String[] args = this.args.split(";");
		switch(type)
		{
		case 1://Aller voir
			if(player.get_isTalkingWith() == Integer.parseInt(args[0]))
			{
				isFinished = true;
			}
			break;
		case 2://Montrer à
			if(player.hasItemTemplate(Integer.parseInt(args[0]), Integer.parseInt(args[1])))
			{
				isFinished = true;
			}
			break;
		case 3://Ramener à
			if(player.hasItemTemplate(Integer.parseInt(args[0]), Integer.parseInt(args[1])))
			{
				player.removeByTemplateID(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
				isFinished = true;
			}
			break;
		case 4://Découvrir la carte
			if(player.get_curCarte().get_id() == Integer.parseInt(args[0]))
			{
				isFinished = true;
			}
			break;
		case 5://Découvrir la zone
			if(player.get_curCarte().getSubArea().get_id() == Integer.parseInt(args[0]))
			{
				isFinished = true;
			}
			break;
		case 6://Vaincre en un seul combat
			int i = 0;

			for(final String mobs : args)
			{
				final String[] infos = mobs.split(",");
				for(final Fighter fighter : (ArrayList<Fighter>)object)
				{
					if(fighter.getMob() != null && fighter.getMob().getTemplate().getID() == Integer.parseInt(infos[0]))
					{
						i++;
					}
				}
				if(i >= Integer.parseInt(infos[1]))
				{
					isFinished = true;
				}
			}
			break;
		case 7://Monstre à vaincre FIXME: maybe more than 1 mobs to kill
			for(final String mob : args)
			{
				for(final Fighter fighter : ((ArrayList<Fighter>)object))
				{
					if(fighter.isMob() && fighter.getMob().getTemplate().getID() == Integer.parseInt(mob))
					{
						isFinished = true;
					}
				}
			}
			break;
		case 8://Utiliser
			if(player.getLastItemUsed() == Integer.parseInt(args[0]))
			{
				isFinished = true;
			}
			break;
		case 9://Retourner voir
			//TODO: Faire un checker pour voir s'il a déjà parler au pnj ??
			if(player.get_isTalkingWith() == Integer.parseInt(args[0]))
			{
				isFinished = true;
			}
			break;
		/*
		case 10://Escorter

			break;
		*/
		case 11://Vaincre un joueur en défi
			if(player.get_fight().get_type() == Constants.FIGHT_TYPE_CHALLENGE)
			{
				final Personnage target = ((ArrayList<Fighter>) object).get(0).getPersonnage(); //Pas sûr du fonctionnement
				if(target != null)
				{
					final int diff = Math.round(target.get_lvl()-player.get_lvl());
					if(diff >= 0 && diff <= 25)
					{
						isFinished = true;
					}
				}
			}
			break;
		case 12://Ramener âmes
			final ArrayList<Objet> toRem = new ArrayList<Objet>();
			final String[] infosSoul = args[1].split(":");
			int soulQua = Integer.parseInt(args[1]);
			if(player.get_isTalkingWith() == Integer.parseInt(args[0]))
			{
				for(final Objet item : player.getItems().values())
				{
					for(final Entry<Integer, Integer> curStat : item.getStats().getMap().entrySet())
					{
						if(curStat.getKey() == 623 && curStat.getValue() == Integer.parseInt(infosSoul[0]))
						{
							if(!toRem.contains(item))
								toRem.add(item);
							soulQua--;
						}
					}
				}
				if(soulQua <= 0)
				{
					isFinished = true;
				}
				for(final Objet toRemove : toRem)
				{
					player.removeItem(toRemove.getGuid());
				}
			}
			break;
		case 13://Eliminer
			for(final String mob : args)
			{
				for(final Fighter fighter : (ArrayList<Fighter>)object)
				{
					if(fighter.getMob() != null && fighter.getMob().getTemplate().getID() == Integer.parseInt(mob))
					{
						isFinished = true;
					}
				}
			}
			break;
		case 14://Avis de recherche
			for(final String mob : args)
			{
				for(final Fighter fighter : (ArrayList<Fighter>)object)
				{
					if(fighter.getMob() != null && fighter.getMob().getTemplate().getID() == Integer.parseInt(mob))
					{
						isFinished = true;
					}
				}
			}
			break;
		
		default:
			isFinished = true;
			break;
		}
		player.checkQuests();
	}

	public int getId() {
        return id;
    }

	public int getType() {
        return type;
    }

	public String getArgs() {
        return args;
    }

	public boolean isFinished() {
        return isFinished;
    }
}