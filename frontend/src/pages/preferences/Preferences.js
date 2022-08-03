import Topic from './Topic';
import {Link, useNavigate} from 'react-router-dom';
import './preferences.css';
import Navbarr from '../Navbar/Indexx';
function Preferences() {

  const navigate= useNavigate();
    let skills = [
        {id: 0, value: ''},
        {id: 1, value: ''},
        {id: 2, value: ''},
        {id: 3, value: ''},
        {id: 4, value: ''},
        {id: 5, value: ''},
        {id: 6, value: ''},
        {id: 7, value: ''},
        {id: 8, value: ''},
        {id: 9, value: ''},
        {id: 10, value: ''},
        {id: 11, value: ''},
        {id: 12, value: ''},
        {id: 13, value: ''},
        {id: 14, value: ''},
        {id: 15, value: ''},
      ];

    let toLearn = [
        {id: 16, value: ''},
        {id: 17, value: ''},
        {id: 18, value: ''},
        {id: 19, value: ''},
        {id: 20, value: ''},
        {id: 21, value: ''},
        {id: 22, value: ''},
        {id: 23, value: ''},
        {id: 24, value: ''},
        {id: 25, value: ''},
        {id: 26, value: ''},
        {id: 27, value: ''},
        {id: 28, value: ''},
        {id: 29, value: ''},
        {id: 30, value: ''},
        {id: 31, value: ''},
    ];

    const idToTopic = (topicId) => {
        topicId = (topicId > 15)? (topicId - 16) : topicId;
        switch (topicId) {
          case 0:
            return 'Computer Architecture';
          case 1:
            return 'Operating Systems';
          case 2:
            return 'Standard Coding';
          case 3:
            return 'Discrete Maths';
          case 4:
            return 'Algorithms';
          case 5:
            return 'Data Structures';
          case 6:
            return 'Compilers';
          case 7:
            return 'Interpreters';
          case 8:
            return 'Databases';
          case 9:
            return 'Networks';
          case 10:
            return 'Design Patterns';
          case 11:
            return 'Web Development';
          case 12:
            return 'Artificial Intelligence';
          case 13:
            return 'Machine Learning';
          case 14:
            return 'Deep Learning';
          case 15:
            return 'Cybersecurity';
          default:
            return 'Subject';
        }
    };


    function expLvl(selected, index) {
        if (index > skills.length - 1) {
            toLearn[index - 16].value = selected;  // the 16 magic number may need to be modified upon adding more topics
        }
        else {
            skills[index].value = selected;
        }
    }


    const contBtn = async () => {
        console.log('skills:');
    const uId= localStorage.getItem('UsersID from user table');
        for (const obj of skills) {
            if (obj.value) {
                console.log(idToTopic(obj.id), obj.value);
                await sendToServer({topic: idToTopic(obj.id), expLvl: obj.value, userId: parseInt(uId)}, 'goodAt');  
                // the 'goodAt' may need to be modified depending on how the server behaves, for now this is for a mock json backend
            }
        }
        console.log('\nTo Learn:');

        for (const obj of toLearn) {
            if (obj.value) {
                console.log(idToTopic(obj.id), obj.value);
                await sendToServer({topic: idToTopic(obj.id), expLvl: obj.value, userId: parseInt(uId)}, 'toLearn');
                // the 'toLearn' may need to be modified depending on how the server behaves, for now this is for a mock json backend
            }
        }
    }


    async function sendToServer(dataItem, where) {
        // The URL will need to be changed with that of the actual server

        const token= 'Bearer '+ localStorage.getItem('token');


        const res = await fetch(`http://localhost:8080/preferences/storePref/${where}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(dataItem)
        }).catch(err => console.log(err));
        return res.json();
    }

    const handleLogout= ()=>{
      localStorage.clear();
      localStorage.setItem('isAuthenticated', false);
    navigate('/login');
    }

    return (
      <>
      {/* <Navbarr/> */}
       {localStorage.getItem('isAuthenticated')==='true' && (
    <div className='d-flex justify-content-between mb-2'>
    </div>
    )}
        <div className='main-grid'>

            <div className="content-selection">
                <div className="topic-bloc">
                    <h3 className='window-header'>&nbsp;&nbsp;&nbsp;Please select topics you're good at:&nbsp;&nbsp;&nbsp;</h3>
                    <h3 className='window-header'>&nbsp;&nbsp;&nbsp;(Recommended 3)</h3>
                    <div className='topic-window'>
                        {skills.map(skill => {
                            return (<Topic key={skill.id} expLvl={expLvl} id={skill.id} idToTopic={idToTopic} />)
                        })}
                    </div>
                </div>
    
                <div className="topic-bloc">
                    <h3 className='window-header'>Please select topics you'd like to learn:</h3>
                    <h3 className='window-header'>(Recommended 3)</h3>
                    <div className='topic-window'>
                        {toLearn.map(learn => {
                            return <Topic key={learn.id} expLvl={expLvl} id={learn.id} idToTopic={idToTopic} />
                        })}
                    </div>
                </div>
            </div>
    
            {/* // Routing tables links will have to be changed to proper routes/paths */}
            <Link to='/guides'><button className='pref-btn skip-btn'>Skip for now...</button></Link>
            <Link to='/guides'><button className='pref-btn continue-btn' onClick={contBtn}>Continue...</button></Link>
            <div className="bottom-white"></div>
      </div>
      </>
    );
}

export default Preferences;
