const express = require('express');
const { connect } = require('http2');
const mysql = require('mysql');

const app = express();

app.use(express.json());
app.use(
  express.urlencoded({
    extended: true,
  })
);
const PORT = process.env.PORT || 8080;

app.get('/', (req, res) => {
  res.send('Hello this is new endpoint');
});

app.get('/test', (req, res) => {
  res.send('{testing}');
});

app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}...`);
});

var con = mysql.createConnection({
  socketPath: '/cloudsql/ivory-bit-323815:us-central1:my-demo-example',
  //   host: 'localhost',
  user: 'root',
  password: 'Root@12345',
  database: 'sitepoint',
});

app.get('/authors', (req, res) => {
  console.log('Start Connecting');
  con.connect(function (err) {
    if (err) {
      console.log('Connection ERROR!', err);
      throw err;
    }
    console.log('Connected!');
  });
  getResult()
    .then((result) => {
      res.send(result).status(200);
    })
    .catch((err) => {
      res.send(err).status(500);
    })
    .finally(() => {
      console.log('Query completed');
    });
});

const getResult = async () => {
  let promiseResult = new Promise((resolve, reject) => {
    con.query('select * from authors', (err, rows) => {
      if (err) {
        console.log('Query ERROR!', err);
        reject(err);
      }
      resolve(JSON.parse(JSON.stringify(rows)));
    });
  });
  //let result = await promiseResult;
  //console.log('result', result);
  return await promiseResult;
};
