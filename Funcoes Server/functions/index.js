const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp();
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

exports.addPlanta = functions.https.onRequest((req, res) => {
    const original = req.query.text;
return admin.database().ref('messages').push(original);
});

// https://us-central1-firebase-plantaplus.cloudfunctions.net/addPlanta
// uppercaseme:{name:"uppercase", age:24}

//exports.saveMessageToken = functions.database.

exports.sendNotification = functions.database.ref('/usuario/{userId}/plantas').onUpdate(event => {

    //get the userId of the person receiving the notification because we need to get their token
    const cpfUsuario = event.params.userId;
    console.log("cpfUsuario: ", cpfUsuario);

    //get the message token
    const messageToken = event.data.child('usuario').child(cpfUsuario).child('messageToken').val();
    console.log("messageToken: ", messageToken);

    //we have everything we need
    //Build the message payload and send the message
    console.log("Construction the notification message.");
    const payload = {
        data: {
            data_type: "direct_message",
            title: "Está na hora de regar a sua planta!",
            message: "Regue a sua rosa do deserto, ela está precisando..."
        }
    };

    return admin.messaging().send(payload);
});