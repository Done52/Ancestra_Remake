package objects;

import java.util.ArrayList;

import common.SocketManager;
import common.World;
import common.World.Couple;

public class Quest {
	
	private final int id;
	private final String conditions;
	private final ArrayList<QuestStep> steps = new ArrayList<QuestStep>();
	private QuestStep curStep;
	private boolean isFinished;
	
	public Quest (final int id, final String conditions, final String steps)
	{
		this.id = id;
		this.conditions = conditions;
		for(final String curStep : steps.split(";"))
		{
			try
			{
				this.steps.add(World.getQuestSteps().get(Integer.parseInt(curStep)));
			} catch(final Exception e)
			{
				continue;
			}
		}
		this.curStep = initCurStep();
	}
	
	public void check(final Personnage player)
	{
		boolean allFinished = true;
		for(final QuestObjective objective : curStep.getObjectives())
		{
			if(!objective.isFinished())
				allFinished = false;
		}
		if(!allFinished)
			return;
		if(curStep.getOrder() == steps.size()) //Si plus d'étape on termine la quête
		{
			finish(player);
			giveRewards(player);
		}
		else //Sinon on met à jour la quête et on up l'étape actuel de 1
		{
			update(player);
		}
	}

	private void update(final Personnage player) {
		for(final QuestStep curStep : this.steps)
		{
			if(curStep.getOrder() == (this.curStep.getOrder() + 1))
			{
				this.curStep = curStep;
			}
		}
		SocketManager.GAME_SEND_Im_PACKET(player, "55"+id);
    }

	private void finish(final Personnage player) {
	    setFinished(true);
	    SocketManager.GAME_SEND_Im_PACKET(player, "56"+id);
    }

	private void giveRewards(final Personnage player) {
		if(curStep.getRewardKamas() != 0)
		{
			player.set_kamas(player.get_kamas() + curStep.getRewardKamas());
		}
		if(curStep.getRewardXp() != 0)
		{
			player.addXp(curStep.getRewardXp());
		}
		if(curStep.getRewardItems().size() != 0)
		{
			for(final Couple<Integer, Integer> curObj : curStep.getRewardItems())
			{
				final Objet toAdd = World.getObjTemplate(curObj.first).createNewItem(curObj.second, false);
				if(player.addObjet(toAdd, true))
    				World.addObjet(toAdd, true);
			}
		}
    }

	public int getId() {
	    return id;
    }

	public String getConditions() {
	    return conditions;
    }

	public ArrayList<QuestStep> getSteps() {
	    return steps;
    }
	
	public void setFinished(final boolean b) {
		isFinished = b;
	}
	
	public boolean isFinished() {
	    return isFinished;
    }
	
	public QuestStep initCurStep() {
		int order = 1000;
		QuestStep toReturn = null;
		for(final QuestStep curStep : this.steps)
		{
			if(curStep.getOrder() < order)
			{
				order = curStep.getOrder();
				toReturn = curStep;
			}
		}
		return toReturn;
    }

	public QuestStep getCurStep() {
	    return curStep;
    }
}
