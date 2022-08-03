import './topic-dropdown.css';
import {useState} from 'react';

const TopicDropdown = ({selected, setSelected}) => {

    const options = ['Computer Architecture', 'Operating Systems', 'Standard Coding', 'Discrete Maths', 'Algorithms', 'Data Structures', 
    'Compilers', 'Interpreters', 'Databases', 'Networks', 'Design Patterns', 'Web Development', 'Artificial Intelligence', 
    'Machine Learning', 'Deep Learning', 'Cybersecurity'];

    const [isActive, setIsActive] = useState(false);
    const [experience, setExperience] = useState('Novice');

    function handleRadioChange(event) {
        const exp = event.target.value;
        console.log(exp);
        setExperience(exp);
        setSelected(selected.split(' - ')[0] + ` - ${exp}`);
    }

    return (
        <div className="topic-exp-box">
            <div className="dropdown">
                <div className="dropdown-btn" onClick={(e) => setIsActive(!isActive)}>
                    {selected}
                    <span className="fas fa-caret-down"></span>
                </div>
                {isActive && (
                    <div className='dropdown-content'>
                        {options.map((option, index) => (
                            <div onClick={() => {
                                setSelected(option + ` - ${experience}`);
                                setIsActive(false);
                            }} className='dropdown-item' key={index}>
                                {option}
                            </div>
                        ))}
                    </div>
                )}
            </div>

            {selected === 'Pick a topic...'? null:
            <span className="experience-selection">
                <p>&nbsp;&nbsp;Select difficulty:</p>
                <div className="radios">
                    <input type='radio' className='rb1' name='expRadio' onChange={(event) => handleRadioChange(event)} value='Novice'></input>
                    <input type='radio' className='rb2' name='expRadio' onChange={(event) => handleRadioChange(event)} value='Beginner'></input>
                    <input type='radio' className='rb3' name='expRadio' onChange={(event) => handleRadioChange(event)} value='Intermediate'></input>
                    <input type='radio' className='rb4' name='expRadio' onChange={(event) => handleRadioChange(event)} value='Advanced'></input>
                </div>
            </span>}
        </div>
    );
};

export default TopicDropdown;