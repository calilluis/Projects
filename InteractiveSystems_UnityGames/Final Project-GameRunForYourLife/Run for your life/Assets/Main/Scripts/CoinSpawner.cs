using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CoinSpawner : MonoBehaviour
{
    public bool canSpawn = true;
    public GameObject coinPrefab;
    public List<Transform> coinSpawnPositions = new List<Transform>(); // 3
    public float timeBetweenSpawns;
    public List<GameObject> coinList = new List<GameObject>();

    private void SpawnCoin()
    {
        Vector3 randomPosition = coinSpawnPositions[Random.Range(0, coinSpawnPositions.Count)].position; //error -------------------------------------------------------------------------------------------------------
        GameObject obstacle = Instantiate(coinPrefab, randomPosition, coinPrefab.transform.rotation);
        coinList.Add(obstacle);
        obstacle.GetComponent<Coin>().SetSpawner(this);
    }

    public void DestroyAllObstacles()
    {
        foreach (GameObject obstacle in coinList)
        {
            Destroy(obstacle);
        }

        coinList.Clear();
    }
    private IEnumerator SpawnRoutine()
    {
        while (canSpawn)
        {
            SpawnCoin(); 
            yield return new WaitForSeconds(timeBetweenSpawns);
        }
    }
    public void RemoveObstacleFromList(GameObject obstacle)
    {
        coinList.Remove(obstacle);
    }

    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine(SpawnRoutine());
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
