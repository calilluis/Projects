using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class UIManager : MonoBehaviour
{
    public static UIManager Instance; 

    public Text obstaclesAvoidedText; 
    public Text hitsbyObstacleText;
    public Text hitbyCoinText;
    public Text gameOverText;
    public double points;
    public double nextlevel;

    public int difficulty = 0;

    public Obstacle obs;
    public Coin coin;
    public Scenary scenarytree;
    public Scenary scenaryrock;
    public Scenary scenarymountain;
    public MovingTexture ground;

    void Awake()
    {
        Instance = this;
        nextlevel = 500;
        gameOverText.GetComponent<Text>().enabled = false;
        

    }

    private void Update()
    {
        points += 0.05 + (difficulty/2);
        hitbyCoinText.text = Math.Floor(points).ToString();

        if(points > nextlevel)
        {
            difficulty++;
            GameStateManager.Instance.obstaclesHitBeforeGameOver++;
            nextlevel += nextlevel * (2+difficulty);
            obs.updateDificulty(difficulty);
            scenarytree.updateDificulty(difficulty);
            scenaryrock.updateDificulty(difficulty);
            scenarymountain.updateDificulty(difficulty);
            coin.updateDificulty(difficulty);
            ground.ScrollY -= (float)0.05;

        }

    }
    public void UpdatehitsbyObstacles() 
    {
        hitsbyObstacleText.text = GameStateManager.Instance.hitsbyObstacles.ToString() + " / " +  GameStateManager.Instance.obstaclesHitBeforeGameOver.ToString();
    }

    public void UpdateobstaclesAvoided() // 2
    {   
        obstaclesAvoidedText.text = GameStateManager.Instance.obstaclesAvoided.ToString();
    }

    public void UpdatehitbyCoin()
    {
        points += 100;
        hitbyCoinText.text = points.ToString();
    }

}
