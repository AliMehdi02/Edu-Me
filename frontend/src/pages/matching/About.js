import React, { useState, useEffect } from 'react'
import defaultPic from "./defaultProfilePic.JPG";
import NavBar from './NavBar';
import axios from "axios";
import { useParams, useLocation, Link } from 'react-router-dom';
import "./About.css"
const About = () => {
  const [match, setMatch] = useState({});
  const [badpreferences, setBadPreferences] = useState([]);
  const [goodpreferences, setGoodPreferences] = useState([]);
  const { id } = useParams();

  const fetchData = () => {

    const token = 'Bearer ' + localStorage.getItem('token');
    const userUrl = `http://localhost:8080/userById/${id}`
    const badPrefUrl = `http://localhost:8080/preferences/goodAt-preferences/${id}`
    const goodPrefUrl = `http://localhost:8080/preferences/badAt-preferences/${id}`
    const getUser = axios.get(userUrl, {
      headers: {
        'Authorization': token
      }
    }, [])
    const getBadPref = axios.get(badPrefUrl, {
      headers: {
        'Authorization': token
      }
    }, [])
    const getGoodPref = axios.get(goodPrefUrl, {
      headers: {
        'Authorization': token
      }
    }, [])
    axios.all([getUser, getBadPref, getGoodPref]).then(
      axios.spread((...allData) => {
        const allUserDetails = allData[0].data
        const allBadPrefDetails = allData[1].data
        const allGoodPrefDetails = allData[2].data
        setMatch(allUserDetails)
        setGoodPreferences(allGoodPrefDetails)
        setBadPreferences(allBadPrefDetails)
      })
    )

  }

  useEffect(() => {
    fetchData()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])



  const badListOfPref = badpreferences.map((badpreferences) =>
    <li>{badpreferences}</li>);

  const goodListOfPref = goodpreferences.map((goodpreferences) =>
    <li>{goodpreferences}</li>);

  return (
    <div>
      <NavBar />
      <div className="About">
        <h1>About</h1>

        <div className="About-contact">
          <div className="About-contact-intro">
            <p>If you are interested in working together, you can contact me at: </p>
          </div>
          <div className="About-socials">
            <p>{match.email}</p>
          </div>
          <div className="About-picture">
            <img src={defaultPic} className="profile-pic" alt="UserProfile" />
          </div>
        </div>

        <div className='About-intro'>
          <p>Hi! My name is {match.userName}</p>
        </div>
        <div className="About-lists">
          <div className="About-learn-container">
            <div className="About-header">
              <p> I would like to learn</p>
            </div>

            <div className="About-learn-list">
              {badListOfPref}
            </div>





          </div>
          <div className="About-enjoy-container">
            <div className="About-header">
              <p> I am confident in </p>
            </div>
            <div className="About-enjoy-list">
              {goodListOfPref}
            </div>


          </div>


        </div>


        {/* <p> I would like to learn {badListOfPref} as I struggle with these topics</p> */}
        {/* <p>I enjoy studying {goodListOfPref} </p> */}




      </div>
    </div>

  )
}

export default About