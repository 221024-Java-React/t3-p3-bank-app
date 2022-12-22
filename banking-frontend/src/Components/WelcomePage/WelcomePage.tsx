import React from 'react'
import styled from 'styled-components'
import AboutUs from './AboutUs'
import OurServices from './OurServices'

const Container = styled.div`
    height: 100%;
`
const Wrapper = styled.div`
    height: 100%;
    width: 100%;
`
const TopWrapper = styled.div`
    width: 100%;
    height: 50%;
    display: flex;
`
const WelcomeWrapper = styled.div`
    display: flex;
    flex-direction: column;
    padding-inline: 5%;
    padding-block: 2%;
    width: 50%;
    height: 100%;
    justify-content: center;
    flex: 1;
`
const WelcomeTitle = styled.h1`
    letter-spacing: 2px;
    font-size: 2.5em;
    margin-bottom: 15px;
`
const WelcomeParagraph = styled.p`
    letter-spacing: 1px;
    margin-bottom: 10px; 
    width: 100%;
    font-size: 1.2em;
`
const ImageWrapper = styled.div`
    width: 50%;
    height: fit-content;
    display: flex;
    justify-content: center;
    padding-top: 2%;
`
const Image = styled.img`
    width: 50%;
    height: auto;
`
const BottomWrapper = styled.div`
    width: 100%;
    height: 50%;
    display: flex;
`
const ServicesWrapper = styled.div`
    width: 50%;
`
const AboutWrapper = styled.div`
    width: 50%;
`

const WelcomePage: React.FC = () => {
    return (
        <Container>
            <Wrapper>
                <TopWrapper>
                    <WelcomeWrapper>
                        <WelcomeTitle>Welcome to BANK NAME!</WelcomeTitle>
                        <WelcomeParagraph>
                            We're more than just any other bank, we're family helping families!
                        </WelcomeParagraph>
                        <WelcomeParagraph>
                            Lorem ipsum dolor sit amet, officia excepteur ex fugiat reprehenderit enim labore culpa sint ad nisi Lorem pariatur mollit ex esse exercitation amet. Nisi anim cupidatat excepteur officia. Reprehenderit nostrud nostrud ipsum Lorem est aliquip amet voluptate voluptate dolor minim nulla est proident. Nostrud officia pariatur ut officia. Sit irure elit esse ea nulla sunt ex occaecat reprehenderit commodo officia dolor Lorem duis laboris cupidatat officia voluptate. Culpa proident adipisicing id nulla nisi laboris ex in Lorem sunt duis officia eiusmod. Aliqua reprehenderit commodo ex non excepteur duis sunt velit enim. Voluptate laboris sint cupidatat ullamco ut ea consectetur et est culpa et culpa duis.
                        </WelcomeParagraph>
                    </WelcomeWrapper>
                    <ImageWrapper>
                        <Image src='https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/9108/bank-clipart-md.png' />
                    </ImageWrapper>
                </TopWrapper>
                <BottomWrapper>
                    <ServicesWrapper>
                        <OurServices />
                    </ServicesWrapper>
                    <AboutWrapper>
                        <AboutUs />
                    </AboutWrapper>
                </BottomWrapper>
            </Wrapper>
        </Container>
    )
}

export default WelcomePage
