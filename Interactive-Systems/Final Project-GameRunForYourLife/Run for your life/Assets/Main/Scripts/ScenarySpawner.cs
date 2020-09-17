using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ScenarySpawner : MonoBehaviour
{
    public bool canSpawn = true;
    public GameObject obstaclePrefab;
    public GameObject rockprefab;
    public List<Transform> obstacleSpawnPositions = new List<Transform>(); // 3
    public float timeBetweenSpawns;
    public List<GameObject> obstacleList = new List<GameObject>();

    private void SpawnObstacle()
    {
        int random = Random.Range(0, obstacleSpawnPositions.Count);
        Vector3 randomPosition = obstacleSpawnPositions[random].position;
        GameObject obstacle = Instantiate(obstaclePrefab, randomPosition, obstaclePrefab.transform.rotation);
        GameObject otherscenary;
        switch (random)
        {
            case 0:
                 otherscenary = Instantiate(rockprefab, obstacleSpawnPositions[1].position, rockprefab.transform.rotation); obstacleList.Add(otherscenary); break;
            case 1:
                 otherscenary = Instantiate(rockprefab, obstacleSpawnPositions[0].position, rockprefab.transform.rotation); obstacleList.Add(otherscenary); break;
        }
        
        obstacleList.Add(obstacle);
        
        obstacle.GetComponent<Scenary>().SetSpawner(this);
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
    public void RemoveObstacleFromList(GameObject obstacle)
    {
        obstacleList.Remove(obstacle);
    }


    void Start()
    {
        StartCoroutine(SpawnRoutine());
    }
}
