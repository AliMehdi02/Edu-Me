import React from 'react'
import {useNavigate, useParams} from 'react-router-dom';
import './MatchMe.css'

function MatchMe() {

  const{user_id}=useParams()
  const navigate = useNavigate();


  const handleClick= (e)=>{
    e.preventDefault();
    navigate(`/matches/${user_id}`);
  }


  return (
    <div className='MatchMe'>
      <button className="MatchMe-MatchButton" type="submit" onClick={()=>navigate(`/matches/${user_id}`)}>Match Me</button>   
    </div>
  )
}

export default MatchMe