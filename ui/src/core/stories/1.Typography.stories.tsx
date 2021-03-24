import type { Meta } from '@storybook/react';

export default {
  title: 'Design system/1.Typography',
  argTypes: {
    backgroundColor: { control: 'color' },
  },
} as Meta;

export const Basic = () => {
  const primary = { fontFamily: 'var(--font-primary)' };
  const secondary = { fontFamily: 'var(--font-secondary)' };
  return (
    <div>
      <p style={primary}>font-primary: sans-serif</p>
      <p style={secondary}>font-secondary: serif</p>

      <hr />

      <h1>h1. Vergesso Asteroids</h1>
      <h2>h2. Vergesso Asteroids</h2>
      <h3>h3. Vergesso Asteroids</h3>
      <h4>h4. Vergesso Asteroids</h4>
    </div>
  );
};

const text =
  "General Kenobi, years ago you served my father in the Clone Wars. Now he begs you to help him in his struggle against the Empire. I regret that I am unable to present my father's request to you in person, but my ship has fallen under attack and I'm afraid my mission to bring you to Alderaan has failed. I have placed information vital to the survival of the Rebellion into the memory systems of this R2 unit. My father will know how to retrieve it. You must see this droid safely delivered to him on Alderaan. This is our most desperate hour. Help me, Obi-Wan Kenobi, you're my only hope.";

export const TextScale = () => {
  const sizes = ['xxxl', 'xxl', 'xl', 'lg', 'md', 'sm', 'xs'];

  const paragraphs = sizes.map((size) => (
    <div>
      <h4>.text--{size}</h4>
      <article className={`text--${size}`}>{text}</article>
      <hr />
    </div>
  ));

  return <div>{paragraphs}</div>;
};

export const TextContainer = () => {
  return (
    <div className="text-container">
      <h2>h2. Vergesso Asteroids</h2>
      <p>{text}</p>
      <ul>
        <li>Shug Ninx</li>
        <li>Beru Lars</li>
        <li>Bodo Baas</li>
        <li>Ken</li>
        <li>Nien Nunb</li>
      </ul>
      <p>
        Their primary target will be the <a href="">power generators</a>.
        Prepare to open the shield. Sir, <strong>Rebel ships</strong> are coming
        into our sector. Good. Our <em>first catch</em> of the day. Stand by,
        ion control....Fire! The first transport is away.
      </p>

      <h3>h3. Vergesso Asteroids</h3>
      <p>{text}</p>
      <ol>
        <li>Mon Mothma</li>
        <li>Tenel Ka</li>
        <li>Ponda Baba</li>
        <li>Admiral Ackbar</li>
        <li>Figrin D'an</li>
      </ol>

      <h4>h4. Vergesso Asteroids</h4>
      <p>{text}</p>
    </div>
  );
};
