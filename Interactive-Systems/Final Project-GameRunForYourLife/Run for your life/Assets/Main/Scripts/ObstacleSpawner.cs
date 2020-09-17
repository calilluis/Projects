using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObstacleSpawner : MonoBehaviour
{
    public bool canSpawn = true; 
    public GameObject obstaclePrefab; 
    public List<Transform> obstacleSpawnPositions = new List<Transform>(); // 3
    public float timeBetweenSpawns; 
    public List<GameObject> obstacleList= new List<GameObject>();
    
    private void SpawnObstacle()
    {
        Vector3 randomPosition = obstacleSpawnPositions [Random.Range(0, obstacleSpawnPositions.Count)].position; 
        GameObject obstacle = Instantiate(obstaclePrefab, randomPosition , obstaclePrefab.transform.rotation); 
        obstacleList.Add(obstacle);
        obstacle.GetComponent<Obstacle>().SetSpawner(this); 
    }
    public void DestroyAllObstacles()
    {
        foreach (GameObject obstacle in obstacleList) 
        {
            Destroy(obstacle); 
        }

        obstacleList.Clear();
    }
    private IEnumerator SpawnRoutine() 
    {
        while (canSpawn) 
        {
            SpawnObstacle(); 
            yield return new WaitForSeconds(timeBetweenSpawns); 
        }
    }
    public void RemoveObstacleFromList (GameObject obstacle)
    {
        obstacleList.Remove(obstacle);
    }


    void Start()
    {
        StartCoroutine (SpawnRoutine());
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}

